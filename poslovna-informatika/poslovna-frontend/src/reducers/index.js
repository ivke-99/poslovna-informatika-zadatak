import {configureStore} from '@reduxjs/toolkit';
import {persistReducer} from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import interfaceReducer from './interfaceReducer';
import {combineReducers} from 'redux';

const persistConfig = {
  key: 'store-v1',
  storage: storage,
  whitelist: [
    'interface',
  ],
};

const reducers = combineReducers({
  interface: interfaceReducer,
})

const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
  devTools: true
});

export default store;
