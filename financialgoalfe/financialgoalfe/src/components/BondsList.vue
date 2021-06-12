<template>
<v-container>
<v-card-title>
  Bonds List
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
    :items="bondsList"
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
                      v-model="editedItem.cedola"
                      label="CEDOLA"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.expire"
                      label="EXPIRE"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      readonly
                      v-model="editedItem.country.shortName"
                      label="COUNTRY"
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
                @click="axiosBuyBond"
              >
                Buy
              </v-btn>
              <v-btn
                color="red"
                text
                @click="axiosSellBond"
                v-if="operation === 'Sell' && editedItem.quantity !== undefined"
              >
                Sell
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
    </template>
    <template v-slot:item.country="{ item }">
      {{item.country.shortName}}
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
            @click="buyBond(item)"
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
            @click="sellBond(item)"
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
  import axios from '../api/bond.js'
  export default {
    name: 'BondsList',

    created(){
      this.getAllBonds();
      this.getFoldersOwner();
    },

    data: () => ({
      search: '',
      folderSelected: '',
      folders:[],
      menu2: false,
      infoOperation: '',
      dialogInfo: false,
      date: new Date().toISOString().substr(0, 10),
      dialog: false,
      editedItem:{isin:"",cedola:0,price:0,description:"",expire:"",currencies:{idCurrencies:0,shortName:"",longName:""},country:{idCountry:0,shortName:"",longName:""}},
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
          { text: 'EXPIRE', value: 'expire' },
          { text: 'CEDOLA', value: 'cedola' },
          { text: 'DESCRIPTION', value: 'description' },
          { text: 'PRICE', value: 'price' },
          { text: 'COUNTRY', value: 'country' },
          { text: 'CURRENCIES', value: 'currencies'},
          { text: 'ACTIONS', value: 'actions'}
        ],
        bondsList: [{isin:'IT0000013435',expire:'20/01/1998',cedola:100.0,description:'MOLTO FIGO',price:1200.34,country:'ITA',currencies:'EUR'}]
    }),
    computed: {
      formTitle () {
        return this.title
      },
    },


    methods: {
      buyBond (item) {
        this.editedIndex = this.bondsList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.title="Buy Bond ISIN "+this.editedItem.isin
        this.dialog = true
        this.operation="Buy"
      },

      sellBond (item) {
        this.editedIndex = this.bondsList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.title="Sell Bond ISIN "+this.editedItem.isin
        this.dialog = true
        this.operation="Sell"
      },

      deleteItemConfirm () {
        this.bondsList.splice(this.editedIndex, 1)
        this.closeDelete()
      },

      axiosBuyBond () {
        let body = {}
        body.isin = this.editedItem.isin
        body.price = this.editedItem.price
        body.quantity = this.editedItem.quantity
        body.folder = this.folderSelected
        body.typeOfDeal = 'B'
        body.note = ''
        body.owner = 1
        body.executionDate = this.date
        console.log(body)
        axios.axiosOperationBonds(body).then(
          response => {
            this.infoOperation = response.data
            this.dialogInfo = true
          }
        )
        this.dialog = false
      },

      axiosSellBond () {
        let body = {}
        body.isin = this.editedItem.isin
        body.price = this.editedItem.price
        body.quantity = this.editedItem.quantity
        body.folder = this.folderSelected
        body.typeOfDeal = 'S'
        body.note = ''
        body.owner = 1
        body.executionDate = this.date
        console.log(body)
        axios.axiosOperationBonds(body).then(
          response => {
            this.infoOperation = response.data
            this.dialogInfo = true
          }
        )
        this.dialog = false
      },

      getAllBonds(){
        axios.axiosGetAllBonds().then(
          response => {
            this.bondsList = response.data
            let i = 0
            this.bondsList.forEach(element => {
              element 
              this.bondsList[i].expire = this.bondsList[i].expire.substr(0, 10)
              i++;
            });
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
