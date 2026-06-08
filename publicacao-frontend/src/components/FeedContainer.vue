<template>
  <div class="row justify-center q-pt-md">
    <div class="col-12 col-md-8 col-lg-6">
      <CreatePost />

      <div class="flex justify-end q-mb-md">
        <q-select
          v-model="sortBy"
          :options="sortOptions"
          label="Ordenar por"
          outlined
          dense
          style="min-width: 150px"
        />
      </div>

      <q-card v-for="post in posts" :key="post.publicacao_id" class="q-mb-md">
        <PostCard :post="post" @salva-like="atualizaPublicacao" @salva-post="atualizaPublicacao"/>
      </q-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import CreatePost from 'components/feed/CreatePost.vue';
import PostCard from 'components/feed/PostCard.vue';
import type { Publicacao } from 'components/feed/types';
import { buscaFeedGlobal } from 'src/domain/feed/FeedGlobal';

const posts = ref<Publicacao[]>([]);

const pagina = ref(0);
const tamanho = ref(100);

const sortBy = ref('recent');
const sortOptions = ['recent', 'popular', 'following'];

const loadingPage = async () => {
  const response = await buscaFeedGlobal(pagina.value, tamanho.value);
  posts.value = response;
};

const atualizaPublicacao = (postAtualizado: Publicacao) => {
  const findIndex = posts.value.findIndex((e) => e.publicacao_id === postAtualizado.publicacao_id);
  if (findIndex !== -1) {
    const post = posts.value[findIndex];

    if (post) {
      post.likes.length = postAtualizado.likes.length;
      post.likes = postAtualizado.likes;
      post.comentarios = postAtualizado.comentarios;
    }
  }
};

onMounted(async () => {
  await loadingPage();
});
</script>
