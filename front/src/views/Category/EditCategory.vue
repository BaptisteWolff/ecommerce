<script setup>

</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h4 class="pt-3">Éditer une catégorie</h4>
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
          <button type="button" class="btn btn-primary" @click="editCategory">Valider</button>
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
      id: null,
      name: null,
      description: null,
      imageUrl: null,
    }
  },
  props: ["baseURL", "categories"],
  methods: {
    async editCategory() {
      const updatedCategory = {
        id: this.id,
        name: this.name,
        description: this.description,
        imageUrl: this.imageUrl,
      };

      await axios({
        method: "post",
        url: this.baseURL + "category/" + this.id,
        data: JSON.stringify(updatedCategory),
        headers: {
          "Content-Type": "application/json",
        }
      })
          .then(() => {
            console.log("Category updated successfully");
          })
          .catch((error) => {
            console.log(error)
          })
    }
  },
  mounted() {
    this.id = this.$route.params.id;
    this.category = this.categories.filter(category => category.id == this.id)[0]
    this.name = this.category.name
    this.description = this.category.description
    this.imageUrl = this.category.imageUrl
  }
}
</script>

<style scoped>

</style>