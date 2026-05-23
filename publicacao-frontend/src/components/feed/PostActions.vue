<template>
  <!-- Área de comentário -->
  <transition name="fade">
    <q-card-section v-show="comentar" class="card-comentario">
      <!-- Input -->
      <div class="comentario-input">
        <q-input
          ref="postInput"
          v-model="postComentario"
          placeholder="Adicione um comentário..."
          type="textarea"
          autogrow
          borderless
          class="post-input"
        />
      </div>
    </q-card-section>
  </transition>
  <div class="acao-enviar">
    <transition name="fade">
      <q-btn
        v-show="postComentario.length > 0"
        dense
        flat
        icon="send"
        label="Enviar"
        @click="enviarComentario"
      />
    </transition>
  </div>

  <!-- Ações -->
  <q-card-actions class="flex justify-around">
    <q-btn flat icon="thumb_up_alt" :text-color="props.curtida" label="Curtir" @click="curtiPubicacao"/>
    <q-btn flat icon="comment" label="Comentar" @click="comentarPublicacao" />
    <q-btn flat icon="share" label="Compartilhar" />
    <!-- <q-btn flat icon="send" label="Enviar" /> -->
  </q-card-actions>
</template>

<script setup lang="ts">
import { ref, nextTick } from 'vue';
const props = defineProps<{
  curtida: string;
}>();

const postComentario = ref('');
const comentar = ref(false);
const postInput = ref();
const emit = defineEmits(['comentario', 'curtir']);

const comentarPublicacao = async () => {
  comentar.value = !comentar.value;

  if (comentar.value) {
    await nextTick();
    postInput.value?.focus();
  }
};

const enviarComentario = () => {
  comentar.value = !comentar.value;
  emit('comentario', postComentario.value);
  postComentario.value = '';
};

const curtiPubicacao = () => {
  emit('curtir');
};
</script>

<style scoped>
.card-comentario {
  display: flex;
  align-items: flex-end;
  gap: 8px;
  width: 100%;
  padding-top: 8px;
}

.comentario-input {
  flex: 2;
}
.acao-enviar {
  padding-left: 2%;
}

.post-input {
  font-size: 0.95rem;
  border-radius: 20px;
  padding: 8px 12px;
  background: #f3f2ef;
}

.btn-enviar {
  background: #0a66c2;
  color: white;
  font-weight: 600;
  padding: 6px 14px;
}

/* animação suave */
.fade-enter-active,
.fade-leave-active {
  transition: all 0.25s ease;
  overflow: hidden;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-8px);
  max-height: 0;
}

.fade-enter-to,
.fade-leave-from {
  opacity: 1;
  transform: translateY(0);
  max-height: 200px;
}
</style>
