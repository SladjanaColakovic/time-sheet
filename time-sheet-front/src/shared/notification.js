import { NotificationManager } from "react-notifications";

export const errorNotification = (message) => {
    NotificationManager.error(message, '', 5000);
}

export const successNotification = (message) => {
    NotificationManager.success(message, '', 5000);
}