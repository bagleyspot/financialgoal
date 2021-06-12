import axios from 'axios'

export default {
    axiosGetDatiChartByDate(){
        return axios.get("http://localhost:8080/statistics/2021-01-01/2022-01-01");
    },
}