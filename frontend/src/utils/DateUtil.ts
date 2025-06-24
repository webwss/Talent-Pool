import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';

dayjs.extend(utc);

export const DateUtil = {
    formatDate(dateStr: string) {
        return dayjs(dateStr).format('YYYY-MM-DD HH:mm:ss');
    }
}
