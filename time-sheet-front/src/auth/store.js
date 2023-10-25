import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice'
import { loadFromLocalStorage, saveToLocalStorage } from "./persistance";

const persistedSate = loadFromLocalStorage();

export const store =  configureStore({
    reducer: {
        user: userReducer
    },
    preloadedState: persistedSate
})
store.subscribe(() => saveToLocalStorage(store.getState()))

