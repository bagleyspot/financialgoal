import axios from 'axios'

export default {
    axiosGetFolderOwner(body){
        return axios.get("http://localhost:8080/allfolderowner/"+body);
    },

    axiosGetAllEquitybyOwner(body,select){
        return axios.get('http://localhost:8080/allequitybyowner/'+body+'/'+select);
    },

    axiosGetAllBondByOwner(body,select){
        return axios.get('http://localhost:8080/allbondbyowner/'+body+'/'+select)
    },

    axiosGetEquityDealByIsinAndOwner(isin,owner){
        return axios.get('http://localhost:8080/equitydealbyisin/'+isin+'/'+owner)
    },

    axiosGetBondDealByIsinAndOwner(isin,owner){
        return axios.get('http://localhost:8080/bonddealbyisin/'+isin+'/'+owner)
    }
}