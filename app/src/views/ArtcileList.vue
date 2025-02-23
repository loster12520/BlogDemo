<template>
  <div id="ArticleList">
    <a-flex wrap="wrap" gap="small" class="Flex">
      <router-link :to="`/article/${data.id}`" v-for="data in datas" :key="data.id" class="Cards" >
        <a-card :title="data.title">
          <p>author: {{ data.author }}</p>
          <p>description: {{ data.description }}</p>
        </a-card>
      </router-link>
    </a-flex>
  </div>
</template>

<script setup>
import {ref} from "vue";
import axios from "axios";

const datas = ref([])

axios.get("http://localhost:8080/article/information").then(
    (response) => {
      datas.value = response.data.data
    }
)
</script>

<style scoped>
#ArticleList .Flex {
  padding: 5vh;
  gap: 1vh;
}

#ArticleList .Cards {
  width: 20vh;
  margin: auto;
}

#ArticleList p {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>