<template>
  <q-card>
    <PostHeader :author="post.author" :date="post.date" />

    <q-card-section>
      <p>{{ post.content }}</p>
      <q-img v-if="post.image" :src="post.image" :ratio="16 / 9" />
    </q-card-section>

    <PostStats :likes="post.likes" :comments="post.comments" :shares="post.shares" />

    <q-separator />

    <PostActions />
  </q-card>
</template>

<script setup lang="ts">
import PostHeader from './PostHeader.vue';
import PostStats from './PostStats.vue';
import PostActions from './PostActions.vue';
import type { Post } from './types';

defineProps<{
  post: Post;
}>();
</script>

<style scoped>
.q-card {
  border-radius: 8px;
}
</style>
