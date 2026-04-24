<template>
  <q-card>
    <PostHeader :author="post.author" :date="post.date" />

    <q-card-section>
      <p>{{ post.content }}</p>
      <q-img v-if="post.image" :src="post.image" :ratio="16 / 9" />
    </q-card-section>

    <PostStats
      :likes="post.likes"
      :comments="post.comments.length"
      :shares="post.shares"
      @click-comentario="clickComentario"
    />

    <q-separator />
    <ComentarioCard :showcomentario="showcomentario" :comments="post.comments" />
    <PostActions @comentario="salvaComentario" />
  </q-card>
</template>

<script setup lang="ts">
import PostHeader from './PostHeader.vue';
import PostStats from './PostStats.vue';
import PostActions from './PostActions.vue';
import ComentarioCard from '../ComentarioCard.vue';
import type { Post } from './types';
import { salveComents } from 'src/domain/comentarioService';
import { Notify } from 'quasar';
import { ref } from 'vue';
import { publishGetById } from 'src/domain/publishUser';

const props = defineProps<{
  post: Post;
}>();

const showcomentario = ref(false);

const salvaComentario = async (e: string) => {
  try {

    await salveComents({
      conteudo: e,
      dataPublicacao: new Date(),
      publicacao_id: props.post.id,
    });

    const post = await publishGetById(props.post.id);
    props.post.comments = post.comentarios;

    Notify.create({
      type: 'positive',
      message: 'Comentário enviado com sucesso!',
      position: 'top-right',
    });
  } catch (error) {
    Notify.create({
      type: 'negative',
      message: 'Erro ao enviar comentário',
      position: 'top-right',
    });
  }
};

const clickComentario = () => {
  showcomentario.value = !showcomentario.value;
};
</script>

<style scoped>
.q-card {
  border-radius: 8px;
}
</style>
