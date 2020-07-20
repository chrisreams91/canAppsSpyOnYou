/* eslint-disable no-bitwise */
import AsyncStorage from '@react-native-community/async-storage';

export const getUserId = async () => {
  const userId = await AsyncStorage.getItem('USER_ID');
  if (userId) {
    return userId;
  } else {
    const newUserId = uuidv4();
    await AsyncStorage.setItem('USER_ID', newUserId);
    return newUserId;
  }
};

export const uuidv4 = () => {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = (Math.random() * 16) | 0,
      v = c === 'x' ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
};
