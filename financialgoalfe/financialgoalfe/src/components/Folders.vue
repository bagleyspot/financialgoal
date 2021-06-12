<template>
    <v-container>
        <v-card-title>
            Folders
        </v-card-title>
        <v-toolbar
            height=80
            width=500
            dark
        >
        <v-toolbar-title>Chose Folder</v-toolbar-title>
        <v-autocomplete
            label="Folder"
            class="mx-4"
            flat
            :items="listFolders"
            :search-input.sync="search"
            v-model="select"
            dark
        >
        </v-autocomplete>
        </v-toolbar>
        <v-row
            style="margin-top:2%"
        >
        <v-col>
        <v-data-table
            :headers="headers"
            :items="listEquity"
            sort-by="isin"
            class="elevation-1"
            v-if="select !== null"
            dark
        >
            <template v-slot:item.currencies="{ item }">
                {{item.currencies.shortName}}
            </template>
            <template v-slot:item.actions="{ item }">
                    <v-btn
                    rounded
                    color="primary"
                    dark
                    @click="showDeal(item.isin)"
                    >
                        Show Deals
                    </v-btn>
            </template>
        </v-data-table>
            </v-col>
            <v-col>
                <v-card
                    v-if="showDealDialog===true"
                    dark
                >
                    <v-row>
                        <v-col>
                            <v-card-title>Deals</v-card-title>
                        </v-col>
                        <v-col>
                            <v-card
                                max-width=300
                            >
                                <v-card-title
                                    class="justify-center"
                                >
                                Yield
                                </v-card-title>
                                <v-card-title
                                    v-if="!this.yield.toString().includes('-')"
                                    class=" green--text justify-center"
                                >
                                    {{this.yield}}
                                </v-card-title>
                                <v-card-title
                                    v-if="this.yield.toString().includes('-')"
                                    class=" red--text justify-center"
                                >
                                    {{this.yield}}
                                </v-card-title>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-data-table
                        :headers="headersDeal"
                        :items="listEquityDeal"
                        sort-by="movementDate"
                        class="elevation-1"
                        dark
                    >
                    </v-data-table>
                </v-card>
            </v-col>
        </v-row>
    </v-container>    
</template>
<script>
import axios from '../api/folder.js'
export default {
    name : 'Folders',

    created(){
        this.getFoldersByOwner()
        this.getEquityByOwner()
    },

    data: () => ({
        listFolders:[],
        search: null,
        select: null,
        showDealDialog: false,
        listEquityDeal:[],
        listEquity:[],
        yield: 0,
        headers: [
          {
            text: 'ISIN',
            align: 'start',
            sortable: false,
            value: 'isin',
          },
          { text: 'CURRENCIES', value: 'currencies'},
          { text: 'ACTIONS', value: 'actions'}
        ],
        headersDeal: [
          {
            text: 'ISIN',
            align: 'start',
            sortable: false,
            value: 'isin',
          },
          { text: 'OP. DATE', value: 'movementDate'},
          { text: 'PRICE', value: 'price'},
          { text: 'QUANTITY', value: 'quantity'},
          { text: 'OPERATION', value: 'typeOfDeal'},
          { text: 'ACTIONS', value: 'actions'}
        ],

    }),

    methods:{
        getFoldersByOwner(){
            axios.axiosGetFolderOwner(1).then(
                response => {
                    this.listFolders = response.data
                }
            )
        },
        getEquityByOwner(){
            axios.axiosGetAllEquitybyOwner(1,this.select).then(
                response => {
                    this.listEquity = response.data
                    axios.axiosGetAllBondByOwner(1,this.select).then(
                        response => {
                            let listBonds = response.data
                            this.listEquity = [...this.listEquity,...listBonds]
                        }
                    )
                }
            )
        },

        showDeal(isin){
            this.showDealDialog=true
            axios.axiosGetEquityDealByIsinAndOwner(isin,1).then(
                response => {
                    this.listEquityDeal = response.data
                    let i = 0
                    this.listEquityDeal.forEach(element => {
                        element 
                        this.listEquityDeal[i].movementDate = this.listEquityDeal[i].movementDate.substr(0, 10)
                        i++;
                    });
                    this.yieldCalculation()

                    
                    if(this.listEquityDeal.length === 0){
                        axios.axiosGetBondDealByIsinAndOwner(isin,1).then(
                            response => {
                                this.listEquityDeal = response.data
                                this.listEquityDeal.forEach(element => {
                                    element 
                                    this.listEquityDeal[i].movementDate = this.listEquityDeal[i].movementDate.substr(0, 10)
                                    i++;
                                });
                                this.yieldCalculation()
                            }
                        )
                    }
                }
            )
        },

        yieldCalculation(){
            let tempYieldBuy = 0;
            let tempYieldSell= 0;
            this.listEquityDeal.forEach(element => {
                if(element.typeOfDeal === 'B') tempYieldBuy =tempYieldBuy + element.price*element.quantity
            })
            this.listEquityDeal.forEach(element => {
                if(element.typeOfDeal === 'S') tempYieldSell =tempYieldSell + element.price*element.quantity
            })
            this.yield = tempYieldSell - tempYieldBuy
            this.yield = this.yield.toFixed(5)
        }
    },
    watch: {
        select: function() {this.getEquityByOwner()}
    }

}
</script>
