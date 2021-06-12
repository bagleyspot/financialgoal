<template>
<v-container>
<v-card-title>
  Equity List
  <v-spacer></v-spacer>
  <v-text-field
    v-model="search"
    append-icon="mdi-magnify"
    label="Search"
    single-line
    hide-details
  ></v-text-field>
</v-card-title>
<v-snackbar
    v-model="dialogInfo"
    max-width="500px"
    timeout=2000
    color="red"
    v-if="this.infoOperation.includes('not')"
>
    {{this.infoOperation}}      
</v-snackbar>
<v-snackbar
    v-model="dialogInfo"
    max-width="500px"
    timeout=2000
    color="green"
    v-if="!this.infoOperation.includes('not')"
>
    {{this.infoOperation}}      
</v-snackbar>
  <v-data-table
    :headers="headers"
    :items="equityList"
    sort-by="isin"
    class="elevation-1"
    :search="search"
    dark
  >
    <template v-slot:top>
        <v-spacer></v-spacer>
        <v-dialog
          v-model="dialog"
          max-width="500px"
        >
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            <v-alert
                color="orange"
                dense
                type="info"
                dismissible
            >
            The prices present are indicative, as they are not in real time and may differ from the actual price at the time of the transaction
            </v-alert>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.isin"
                      label="ISIN"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.alfanumericCode"
                      label="ALFANUMERIC CODE"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.market"
                      label="MARKET"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.currencies.shortName"
                      label="CURRENCIES"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.market"
                      label="MARKET"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      filled
                      shaped
                      v-model="editedItem.price"
                      label="PRICE"
                    ></v-text-field>
                  </v-col>
                  
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      filled
                      shaped
                      v-model="editedItem.quantity"
                      label="QUANTITY"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-menu
                      v-model="menu2"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="date"
                        label="Data Execution"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="date"
                      @input="menu2 = false"
                    ></v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                  <v-autocomplete
                  v-model="folderSelected"
                  :items="folders"
                  label="Folder"
                  >
                  </v-autocomplete>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue darken-1"
                text
                @click="close()"
              >
                Cancel
              </v-btn>
              <v-btn
                color="green"
                text
                v-if="operation === 'Buy' && editedItem.quantity !== undefined"
                @click="axiosBuyEquity"
              >
                Buy
              </v-btn>
              <v-btn
                color="red"
                text
                @click="axiosSellEquity"
                v-if="operation === 'Sell' && editedItem.quantity !== undefined"
              >
                Sell
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
    </template>
    <template v-slot:item.currencies="{ item }">
      {{item.currencies.shortName}}
    </template>
    <template v-slot:item.actions="{ item }">
      <v-tooltip bottom>
        <template v-slot:activator="{on,attrs}">
          <v-icon
            v-bind="attrs"
            v-on="on"
            class="mr-2"
            color="green"
            @click="buyEquity(item)"
          >
            mdi-plus-box
          </v-icon>
        </template>
        <span>Buy</span>
      </v-tooltip>
      <v-tooltip bottom>
        <template v-slot:activator="{on,attrs}">
          <v-icon
            v-bind="attrs"
            v-on="on"
            color="red"
            @click="sellEquity(item)"
          >
            mdi-close-box
          </v-icon>
        </template>
        <span>Sell</span>
      </v-tooltip>
    </template>
    <template v-slot:no-data>
      <v-btn
        color="primary"
      >
        Reset
      </v-btn>
    </template>
  </v-data-table>
</v-container>
</template>

<script>
  import axios from '../api/equity.js'
  export default {
    name: 'EquityList',

    created(){
      this.getAllEquity();
      this.getFoldersOwner();
    },

    data: () => ({
      search: '',
      folderSelected: '',
      folders:[],
      infoOperation: '',
      menu2: false,
      date: new Date().toISOString().substr(0, 10),
      dialog: false,
      dialogInfo: false,
      editedItem:{isin:"",alfanumericCode:"",price:0,sector:"",currencies:{idCurrencies:0,shortName:"",longName:""},market:{idMarket:0,longName:"",shortName:"",country:{idCountry:0,longName:"",shortName:""}}},
      operation:'',
      editedIndex:-1,
      title:"",
        headers: [
          {
            text: 'ISIN',
            align: 'start',
            sortable: false,
            value: 'isin',
          },
          { text: 'ALFANUMERIC CODE', value: 'alfanumericCode' },
          { text: 'MARKET', value: 'market' },
          { text: 'PRICE', value: 'price' },
          { text: 'SECTOR', value: 'sector' },
          { text: 'CURRENCIES', value: 'currencies'},
          { text: 'ACTIONS', value: 'actions'}
        ],
        equityList: []
    }),
    computed: {
      formTitle () {
        return this.title
      },
    },


    methods: {
      buyEquity (item) {
        this.editedIndex = this.equityList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.title="Buy Equity ISIN "+this.editedItem.isin
        this.dialog = true
        this.operation="Buy"
      },

      sellEquity (item) {
        this.editedIndex = this.equityList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.title="Sell Equity ISIN "+this.editedItem.isin
        this.dialog = true
        this.operation="Sell"
      },

      deleteItemConfirm () {
        this.equityList.splice(this.editedIndex, 1)
        this.closeDelete()
      },

      axiosBuyEquity () {
        let body = {}
        body.isin = this.editedItem.isin
        body.price = this.editedItem.price
        body.quantity = this.editedItem.quantity
        body.folder = this.folderSelected
        body.typeOfDeal = 'B'
        body.note = ''
        body.owner = 1
        body.movementDate = this.date
        console.log(body)
        axios.axiosOperationEquity(body).then(
          response => {
            this.infoOperation = response.data
            this.dialogInfo = true
          }
        )
        this.dialog = false
      },

      axiosSellEquity () {
        let body = {}
        body.isin = this.editedItem.isin
        body.price = this.editedItem.price
        body.quantity = this.editedItem.quantity
        body.folder = this.folderSelected
        body.typeOfDeal = 'S'
        body.note = ''
        body.owner = 1
        body.movementDate = this.date
        console.log(body)
        axios.axiosOperationEquity(body).then(
          response => {
            this.infoOperation = response.data
            this.dialogInfo = true
          }
        )
        this.dialog = false
      },

      getAllEquity(){
        axios.axiosGetAllEquity().then(
          response => {
            this.equityList = response.data
          }
        )
      },

      close(){
        this.dialog = false;
      },

      getFoldersOwner(){
        axios.axiosGetFolderOwner(1).then(
          response => {
            this.folders = response.data
            this.folderSelected=this.folders[0]
          }
        )
      }
    },
  }
</script>
