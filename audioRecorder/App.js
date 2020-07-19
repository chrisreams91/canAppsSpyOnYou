import React, {Component} from 'react';
import {
  StyleSheet,
  View,
  Button,
  Text,
  FlatList,
  SafeAreaView,
} from 'react-native';
import {Buffer} from 'buffer';
import Sound from 'react-native-sound';
import AudioRecord from 'react-native-audio-record';
import RNFetchBlob from 'rn-fetch-blob';
import axios from 'axios';

export default class App extends Component {
  sound = null;
  state = {
    audioFile: '',
    recording: false,
    loaded: false,
    paused: true,
    data: [],
  };

  async componentDidMount() {
    await this.fetchData(40);
    const options = {
      sampleRate: 16000,
      bitsPerSample: 16,
      wavFile: 'test.wav',
    };

    AudioRecord.init(options);
    AudioRecord.on('data', (data) => {
      const chunk = Buffer.from(data, 'base64');
      console.log('chunk size', chunk.byteLength);
    });
  }

  uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (
      c,
    ) {
      var r = (Math.random() * 16) | 0,
        v = c == 'x' ? r : (r & 0x3) | 0x8;
      return v.toString(16);
    });
  }

  fetchData = async (count) => {
    const response = await axios.get(
      `https://catfact.ninja/facts?limit=${count}`,
    );
    const facts = response.data.data.map((fact) => ({
      ...fact,
      id: this.uuidv4(),
    }));
    console.log('fired: ', facts.length);
    this.setState({data: [...this.state.data, ...facts]});
  };

  start = () => {
    console.log('start record');
    this.setState({audioFile: '', recording: true, loaded: false});
    AudioRecord.start();
  };

  stop = async () => {
    if (!this.state.recording) {
      return;
    }
    console.log('stop record');
    let audioFile = await AudioRecord.stop();
    console.log('audioFile', audioFile);
    this.setState({audioFile, recording: false});
  };

  load = () => {
    return new Promise((resolve, reject) => {
      if (!this.state.audioFile) {
        return reject('file path is empty');
      }

      this.sound = new Sound(this.state.audioFile, '', (error) => {
        if (error) {
          console.log('failed to load the file', error);
          return reject(error);
        }
        this.setState({loaded: true});
        return resolve();
      });
    });
  };

  play = async () => {
    if (!this.state.loaded) {
      try {
        await this.load();
      } catch (error) {
        console.log(error);
      }
    }

    this.setState({paused: false});
    Sound.setCategory('Playback');

    this.sound.play((success) => {
      if (success) {
        console.log('successfully finished playing');
      } else {
        console.log('playback failed due to audio decoding errors');
      }
      this.setState({paused: true});
      // this.sound.release();
    });
  };

  pause = () => {
    this.sound.pause();
    this.setState({paused: true});
  };

  upload = async () => {
    if (!this.state.loaded) {
      try {
        await this.load();
      } catch (error) {
        console.log(error);
      }
    }

    const {
      respInfo: {status},
    } = await RNFetchBlob.fetch(
      'POST',
      'http://localhost:8080/audioRecording',
      {
        'Content-Type': 'multipart/form-data',
        'user-id': 'Chris',
      },
      [
        {
          name: 'audio',
          filename: 'test.wav',
          data: RNFetchBlob.wrap(this.state.audioFile),
        },
      ],
    );

    console.log('status: ', status);
  };

  render() {
    const {recording, paused, audioFile} = this.state;
    return (
      <SafeAreaView style={styles.container}>
        <View style={styles.audioContainer}>
          <View style={styles.row}>
            <Button onPress={this.start} title="Record" disabled={recording} />
            <Button onPress={this.stop} title="Stop" disabled={!recording} />
            {paused ? (
              <Button onPress={this.play} title="Play" disabled={!audioFile} />
            ) : (
              <Button
                onPress={this.pause}
                title="Pause"
                disabled={!audioFile}
              />
            )}
            <Button
              onPress={this.upload}
              title="upload"
              disabled={!audioFile}
            />
          </View>
        </View>
        <FlatList
          contentContainerStyle={styles.flatList}
          data={this.state.data}
          keyExtractor={(item) => item.id}
          renderItem={({item}) => (
            <View>
              <Text style={styles.flatListItem}>{item.fact}</Text>
            </View>
          )}
          onEndReached={() => this.fetchData(20)}
          onEndReachedThreshold={0.5}
        />
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  audioContainer: {marginTop: 40, marginBottom: 10},
  row: {
    flexDirection: 'row',
    justifyContent: 'space-evenly',
  },
  flatList: {
    margin: 15,
    marginTop: 30,
  },
  flatListItem: {
    margin: 20,
  },
});
