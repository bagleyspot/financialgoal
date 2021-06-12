<template>
<v-container>
<v-card-title>
  Loans List
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
    v-if="this.infoOperation === false"
>
    Saving not succefull  
</v-snackbar>
<v-snackbar
    v-model="dialogInfo"
    max-width="500px"
    timeout=2000
    color="green"
    v-if="this.infoOperation === true"
>
    Saving succefull    
</v-snackbar>
  <v-data-table
    :headers="headers"
    :items="loansList"
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
        <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
              @click="clean()"
            >
              New Loan
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.bank"
                      label="BANK"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.descriptionLoan"
                      label="DESCRIPTION"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.duration"
                      label="DURATION"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.typeOfRate"
                      label="TYPE OF RATE"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.rate"
                      label="RATE"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.typeOfLoan"
                      label="TYPE OF LOAN"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.valueLoan"
                      label="VALUE LOAN"
                    ></v-text-field>
                  </v-col>
                  
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.finalPriceLoan"
                      label="FINAL PRICE LOAN"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.monthlyPayment"
                      label="MONTHLY PAYMENT"
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
                        v-model="editedItem.startDate"
                        label="START DATE"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="editedItem.startDate"
                      @input="menu2 = false"
                    ></v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                  <v-menu
                      v-model="menu"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="editedItem.endDate"
                        label="END DATE"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="editedItem.endDate"
                      @input="menu = false"
                    ></v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                  <v-autocomplete
                  v-model="editedItem.currencies"
                  :items="listCurrencies"
                  label="CURRENCIES"
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
                @click="axiosAddEditLoan"
              >
                SAVE
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
    </template>
    <template v-slot:item.country="{ item }">
      {{item.country.shortName}}
    </template>
    <template v-slot:item.currencies="{ item }">
      {{item.currencies}}
    </template>
    <template v-slot:item.actions="{ item }">
      <v-tooltip bottom>
        <template v-slot:activator="{on,attrs}">
          <v-icon
            v-bind="attrs"
            v-on="on"
            class="mr-2"
            @click="modifyLoan(item)"
          >
            mdi-pencil
          </v-icon>
        </template>
        <span>Edit</span>
      </v-tooltip>
      <v-tooltip bottom>
        <template v-slot:activator="{on,attrs}">
          <v-icon
            v-bind="attrs"
            v-on="on"
            color="red"
            @click="deleteItemConfirm(item.idLoan)"
          >
            mdi-delete
          </v-icon>
        </template>
        <span>Delete</span>
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
  import axios from '../api/loan.js'
  export default {
    name: 'LoansList',

    created(){
      this.getAllLoans();
      this.getAllCurrencies();
    },

    data: () => ({
      search: '',
      folderSelected: '',
      folders:[],
      menu2: false,
      menu:false,
      infoOperation: '',
      dialogInfo: false,
      date: new Date().toISOString().substr(0, 10),
      dialog: false,
      editedItem:{isin:"",cedola:0,price:0,description:"",expire:"",currencies:{idCurrencies:0,shortName:"",longName:""},country:{idCountry:0,shortName:"",longName:""}},
      operation:'',
      editedIndex:-1,
      listCurrencies:[],
      title:"",
        headers: [
          {
            text: 'BANK',
            align: 'start',
            sortable: false,
            value: 'bank',
          },
          { text: 'DESCRIPTION', value: 'descriptionLoan' },
          { text: 'DURATION', value: 'duration' },
          { text: 'TYPE OF LOAN', value: 'typeOfLoan'},
          { text: 'MONTHLY PAYMENT', value: 'monthlyPayment' },
          { text: 'VALUE LOAN', value: 'valueLoan' },
          { text: 'FINAL PRICE LOAN', value: 'finalPriceLoan' },
          { text: 'START DATE', value: 'startDate'},
          { text: 'END DATE', value: 'endDate'},
          { text: 'CURRENCIES', value: 'currencies'},
          { text: 'ACTIONS', value: 'actions'}
        ],
        loansList: [{isin:'IT0000013435',expire:'20/01/1998',cedola:100.0,description:'MOLTO FIGO',price:1200.34,country:'ITA',currencies:'EUR'}],
    }),
    computed: {
      formTitle () {
        return this.title
      },
    },


    methods: {
      modifyLoan (item) {
        this.editedIndex = this.loansList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.title="Modify Loan"
        this.dialog = true
      },

      deleteItemConfirm (id) {
        axios.axiosDeleteLoan(id);
        setTimeout(() => {
          this.getAllLoans();
        }, 200);
      },

      axiosAddEditLoan () {
        let body = this.editedItem
        body.idOwner = 1
        axios.axiosAddEditLoan(body).then(
          response => {
            this.infoOperation = response.data
            this.dialogInfo = true
            this.getAllLoans()
          }
        )
        this.dialog = false
      },

      getAllLoans(){
        axios.axiosGetLoanByOwner(1).then(
          response => {
            this.loansList = response.data
            let i = 0
            this.loansList.forEach(element => {
              element 
              this.loansList[i].startDate = this.loansList[i].startDate.substr(0, 10)
              this.loansList[i].endDate = this.loansList[i].endDate.substr(0, 10)
              i++;
            });
          }
        )
      },

      close(){
        this.dialog = false;
        this.editedItem = {}
      },

      clean(){
          this.editedItem = {}
      },

      getAllCurrencies(){
        axios.axiosGetCurrencies().then(
          response => {
            this.listCurrencies = response.data
          }
        )
      }
    },
  }
</script>
