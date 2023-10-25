export const saveToLocalStorage = (state) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem('state', serializedState);
    } catch (e) {
        console.log(e);
    }
}

export const loadFromLocalStorage = () => {
    try {
        const serializedState = localStorage.getItem('state');
        console.log(serializedState)
        if (serializedState === null) return undefined;
        console.log(JSON.parse(serializedState))
        return JSON.parse(serializedState)
    } catch (e) {
        console.log(e);
        return undefined;
    }
}