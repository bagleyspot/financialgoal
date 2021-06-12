import axios from 'axios'

export default {
    axiosGetLoanByOwner(body){
        return axios.get("http://localhost:8080/allLoanByOwner/"+body);
    },

    axiosAddEditLoan(body){
        return axios.post("http://localhost:8080/addLoan/",body);
    },

    axiosGetCurrencies(){
        return axios.get("http://localhost:8080/getCurrencies");
    },

    axiosDeleteLoan(id){
        return axios.get("http://localhost:8080/deleteloan/"+id)
    }
}