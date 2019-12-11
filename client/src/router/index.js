import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/home'
import Room from '@/components/room'
import Cottage from '@/components/cottage'
import Booking from '@/components/booking'

Vue.use(Router)

export default new Router({
  mode: 'history',
  hash: false,
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/booking',
      name:'Booking',
      component: Booking
    },
    {
      path: '/room',
      name: 'Room',
      component: Room
    },
    {
      path: '/cottage',
      name: 'Cottage',
      component: Cottage      
    }
  ]
})
