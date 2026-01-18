<template>
  <nav>
    <!--    <router-link to="/">Home</router-link>-->
    <!--    |-->
    <!--    <router-link to="/about">About</router-link>-->
    <router-view v-if="products && categories"
                 :baseURL="baseURL"
                 :products="products"
                 :categories="categories"
                 @fetchData="fetchData">

    </router-view>
  </nav>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      baseURL: "http://localhost:8081/",
      products: null,
      categories: null,
    }
  },

  methods: {
    async fetchData() {
      await axios.get(this.baseURL + "product/")
          .then(res => this.products = res.data)
          .catch(err => console.log(err))
      await axios.get(this.baseURL + "category/")
          .then(res => this.categories = res.data)
          .catch(err => console.log(err))
    }
  },

  mounted() {
    this.fetchData();
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
