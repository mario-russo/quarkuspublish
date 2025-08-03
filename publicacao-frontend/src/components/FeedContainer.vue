<template>
  <div class="row justify-center q-pt-md">
    <div class="col-12 col-md-8 col-lg-6">
      <CreatePost @new-post="addPost" />

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

      <div v-for="post in sortedPosts" :key="post.id" class="q-mb-md">
        <PostCard :post="post" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import CreatePost from 'components/feed/CreatePost.vue';
import PostCard from 'components/feed/PostCard.vue';
import type { Post } from 'components/feed/types';

// Dados mockados
const posts = ref<Post[]>([
  {
    id: 1,
    author: {
      id: 1,
      name: 'Maria Silva',
      position: 'Desenvolvedora Front-end na Empresa X',
      avatar: 'https://cdn.quasar.dev/img/avatar1.jpg',
    },
    content:
      'Acabei de concluir um projeto incrível usando Vue 3 e Quasar Framework! Foi desafiador mas muito gratificante ver o resultado final. #frontend #vuejs #quasar',
    date: '2023-05-15T10:30:00',
    likes: 24,
    comments: 5,
    shares: 2,
  },
  {
    id: 2,
    author: {
      id: 2,
      name: 'João Santos',
      position: 'CTO na Startup Y',
      avatar: 'https://cdn.quasar.dev/img/avatar2.jpg',
    },
    content:
      'Compartilhando um artigo que escrevi sobre as melhores práticas para gestão de times remotos. Espero que seja útil!',
    date: '2023-05-14T15:45:00',
    image: 'https://cdn.quasar.dev/img/mountains.jpg',
    likes: 56,
    comments: 12,
    shares: 8,
  },
  {
    id: 3,
    author: {
      id: 3,
      name: 'Ana Oliveira',
      position: 'UX Designer na Empresa Z',
      avatar: 'https://cdn.quasar.dev/img/avatar3.jpg',
    },
    content:
      'Estamos contratando UX Designers para nosso time! Se você tem experiência com design system e prototipagem, venha fazer parte dessa jornada conosco.',
    date: '2023-05-12T09:15:00',
    likes: 89,
    comments: 23,
    shares: 15,
  },
]);

const sortBy = ref('recent');
const sortOptions = ['recent', 'popular', 'following'];

const sortedPosts = computed(() => {
  return [...posts.value].sort((a, b) => {
    if (sortBy.value === 'recent') {
      return new Date(b.date).getTime() - new Date(a.date).getTime();
    } else {
      return b.likes - a.likes;
    }
  });
});

const addPost = (content: string) => {
  const newPost: Post = {
    id: posts.value.length + 1,
    author: {
      id: 1,
      name: 'Você',
      position: 'Seu Cargo',
      avatar: 'https://cdn.quasar.dev/img/avatar.png',
    },
    content,
    date: new Date().toISOString(),
    likes: 0,
    comments: 0,
    shares: 0,
  };
  posts.value.unshift(newPost);
};
</script>
