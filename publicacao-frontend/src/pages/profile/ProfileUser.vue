<template>
  <div class="row justify-center q-pt-md">
    <div class="col-12 col-md-8 col-lg-6">
      <div v-for="post in posts" :key="post.id" class="q-mb-md">
        <PostCard :post="post" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import PostCard from 'src/components/feed/PostCard.vue';
import type { Post } from 'src/components/feed/types';
import { PublishUser, publishUser } from 'src/domain/publishUser';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const id = Number(route.params.id);

const posts = ref<Post[]>([]);
function toPost(e: PublishUser): Post {
  return {
    id: e.publicacao_id,
    author: {
      id: e.usuario.id,
      name: e.usuario.nome,
      position: '',
      avatar: `https://i.pravatar.cc/150?img=${e.usuario.id}`,

    },
    content: e.conteudo,
    date: new Date(e.dataPublicacao).toISOString(),
    likes: e.likes.length,
    comments: e.comentarios,
    shares: 0,
  };
}
onMounted(async () => {
  posts.value = (await publishUser(id)).map((e) => toPost(e));
});
</script>
