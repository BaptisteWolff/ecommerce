<script setup>

import "@/views/Category/AddCategory.vue";
</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h4 class="pt-3">Ajouter une catégorie</h4>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-3"></div>
      <div class="col-md-6 px-5 px-md-0">
        <form>
          <div class="form-group">
            <label>Nom</label>
            <input type="text" class="form-control" v-model="name" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <input type="text" class="form-control" v-model="description" required>
          </div>
          <div class="form-group">
            <label>URL de l'image</label>
            <input type="url" class="form-control" v-model="imageUrl" required>
          </div>
          <button type="button" class="btn btn-primary" @click="addCategory">Ajouter</button>
        </form>
      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      name: null,
      description: null,
      imageUrl: null,
    }
  },
  methods: {
    async addCategory() {
      const newCategory = {
        name: this.name,
        description: this.description,
        imageUrl: this.imageUrl,
      };

      const baseURL = "http://localhost:8081/";

      await axios({
        method: "post",
        url: baseURL + "category/",
        data: JSON.stringify(newCategory),
        headers: {
          "Content-Type": "application/json",
        }
      })
      .then(() => {
        console.log("Category added successfully");
      })
      .catch((error) => {
        console.log(error)
      })
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>