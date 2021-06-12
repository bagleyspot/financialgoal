<template>
  <div class="container">
    <line-chart
      v-if="loaded"
      :chartdata="chartdata"
      :styles="myStyles"/>
  </div>
</template>

<script>
import LineChart from './Chart.vue'
import axios from '../api/chart.js'
export default {
  name: 'LineChartContainer',
  components: { LineChart },
  data: () => ({
    loaded: true,
    chartdata: null,
    height: '500px'
  }),
  created(){
      this.getAxiosDataChart();
  },
computed: {
    myStyles () {
      return {
        height: `500px`,
        position: 'relative'
      }
    }
},

  methods: {
      getAxiosDataChart(){
          axios.axiosGetDatiChartByDate().then(
              response => {
                  this.chartdata = {
                      labels: response.data.dataChart.map(item => item.data.substr(0, 10)),
                      datasets: [{
                          label: 'Prova',
                          backgroundColor: '#f87979',
                          data: response.data.dataChart.map(item => item.value)
                            }
                      ]
                  }
              }
          )
      }
  }
}
</script>
