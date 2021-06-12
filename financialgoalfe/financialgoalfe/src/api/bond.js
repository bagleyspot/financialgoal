import axios from 'axios'

export default {
    axiosGetAllBonds(){
        return axios.get('http://localhost:8080/allbonds');
    },

    axiosOperationBonds(body){
        return axios.post("http://localhost:8080/operationbond",body);
    },

    axiosGetFolderOwner(body){
        return axios.get("http://localhost:8080/allfolderowner/"+body);
    }
}