import { NotificationManager } from "react-notifications";

export const notification = (message) => {
    NotificationManager.error(message, '', 5000);
}