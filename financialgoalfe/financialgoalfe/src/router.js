import Vue from 'vue'
import Router from 'vue-router'
import bond from '../src/components/BondsList.vue'
import statistics from '../src/components/statistics.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/bond',
      name: 'bond',
      component: bond
    },
    {
      path:'/',
      name: 'home',
      component: statistics
    }
  ]
})