import axios from 'axios'

export default {
    axiosGetAllEquity(){
        return axios.get('http://localhost:8080/allequity');
    },

    axiosOperationEquity(body){
        return axios.post("http://localhost:8080/operationequity",body);
    },

    axiosGetFolderOwner(body){
        return axios.get("http://localhost:8080/allfolderowner/"+body);
    }
}