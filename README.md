# Can Apps Spy on You ?

The goal of this repo is to create a set of applications that can record audio from a users cell phone, send it to a backend to be parsed into keywords, and then do something such as send an add back to the client based on what was recorded.

## Setting up deepspeech env

taken from https://github.com/mozilla/DeepSpeech

### Create and activate a virtualenv

virtualenv -p python3 $HOME/tmp/deepspeech-venv/
source $HOME/tmp/deepspeech-venv/bin/activate

### Install DeepSpeech

pip3 install deepspeech

### Download pre-trained English model files

curl -LO https://github.com/mozilla/DeepSpeech/releases/download/v0.7.0/deepspeech-0.7.0-models.pbmm
curl -LO https://github.com/mozilla/DeepSpeech/releases/download/v0.7.0/deepspeech-0.7.0-models.scorer

### Download example audio files

curl -LO https://github.com/mozilla/DeepSpeech/releases/download/v0.7.0/audio-0.7.0.tar.gz
tar xvf audio-0.7.0.tar.gz

### Transcribe an audio file

deepspeech --model deepspeech-0.7.0-models.pbmm --scorer deepspeech-0.7.0-models.scorer --audio audio/2830-3980-0043.wav

# TODO

Associate words to some sort of product:
something similar to https://www.merchantwords.com/ ?

Set up in memory database or way to track most popular words:
H2 ?
