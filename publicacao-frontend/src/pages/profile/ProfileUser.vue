<template>
  <SkeletonCardPost v-if="skeleton" />
  <div v-else class="row justify-center q-pt-md">
    <div class="col-12 col-md-8 col-lg-6">
      <div v-for="post in posts" :key="post.publicacao_id">
        <PostCard
          :post="post"
          @salva-post="salvaComentario"
          @salva-like="curtida"
          class="q-mb-md"
        ></PostCard>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import PostCard from 'src/components/feed/PostCard.vue';
import type { Publicacao } from 'src/components/feed/types';
import SkeletonCardPost from 'src/components/SkeletonCardPost.vue';
import { publishUser } from 'src/domain/publishUser';

import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const id = Number(route.params.id);

const posts = ref<Publicacao[]>([]);
const skeleton = ref(false);

const salvaComentario = (postAtualizado: Publicacao) => {
  const findIndex = posts.value.findIndex((e) => e.publicacao_id === postAtualizado.publicacao_id);
  if (findIndex !== -1) {
    const post = posts.value[findIndex];

    if (post) {
      post.comentarios = postAtualizado.comentarios;
    }
  }
};
const curtida = (postAtualizado: Publicacao) => {
  const findIndex = posts.value.findIndex((e) => e.publicacao_id === postAtualizado.publicacao_id);
  if (findIndex !== -1) {
    const post = posts.value[findIndex];

    if (post) {
      post.likes.length = postAtualizado.likes.length;
      post.likes = postAtualizado.likes;
    }
  }
};
onMounted(async () => {
  skeleton.value = !skeleton.value;

  posts.value = await publishUser(id);
  skeleton.value = !skeleton.value;
});
</script>
