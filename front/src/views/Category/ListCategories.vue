<script setup>

</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h4 class="pt-3">Catégories</h4>
        <router-link id="add-category" :to="{name: 'AddCategory'}" v-show="$route.name==='ListCategories'">
          <button class="btn">Ajouter une catégorie</button>
        </router-link>
      </div>
    </div>
    <div class="row">
      <div v-for="category of categories" :key="category.id"
           class="col-md-6 col-xl-4 col-12 pt-3 justify-content-center">
        <div class="card" style="width: 18rem;">
          <img :src="category.imageUrl" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">{{ category.name }}</h5>
            <p class="card-text">{{ category.description }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CategoryList",
  data() {
    return {
      baseURL: "http://localhost:8081/",
      categories: null,
    }
  },
  methods: {
    async getCategories() {
      await axios.get(this.baseURL + "category/")
          .then(res => {
            this.categories = res.data
          })
          .catch(err => {
            console.log(err)
          })
    }
  },
  mounted() {
    this.getCategories();
  }
}
</script>

<style scoped>

</style>