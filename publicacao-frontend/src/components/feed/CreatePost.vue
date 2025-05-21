<template>
  <div>
    <!-- Botão de ação flutuante para mobile -->
    <q-page-sticky position="bottom-right" :offset="[18, 18]" class="lt-md z-fab">
      <q-btn fab icon="add" color="primary" @click="openModal" />
    </q-page-sticky>

    <!-- Card para desktop -->
    <q-card class="q-mb-md gt-sm cursor-pointer" @click="openModal">
      <q-card-section class="flex items-center">
        <q-avatar size="48px" class="q-mr-sm">
          <img :src="userAvatar" />
        </q-avatar>
        <q-input
          v-model="postContent"
          placeholder="Começar uma publicação"
          outlined
          dense
          class="full-width"
          readonly
        />
      </q-card-section>

      <q-card-actions class="flex justify-around">
        <q-btn flat icon="photo" label="Foto" color="primary" />
        <q-btn flat icon="videocam" label="Vídeo" color="primary" />
        <q-btn flat icon="article" label="Artigo" color="primary" />
      </q-card-actions>
    </q-card>

    <!-- Modal para criar postagem -->
    <q-dialog v-model="modalOpen" persistent :position="$q.screen.lt.md ? 'bottom' : 'standard'">
      <q-card :style="dialogStyle">
        <q-card-section class="row items-center q-pb-none">
          <q-btn icon="close" flat round dense @click="closeModal" class="q-mr-sm" />
          <div class="text-h6">Nova publicação</div>
          <q-space />
          <q-btn
            label="Publicar"
            color="primary"
            :disable="!postContent"
            @click="publishPost"
            flat
          />
        </q-card-section>

        <q-card-section class="q-pt-none">
          <div class="flex items-center q-mb-md">
            <q-avatar size="40px" class="q-mr-sm">
              <img :src="userAvatar" />
            </q-avatar>
            <div>
              <div class="text-subtitle2">Seu Nome</div>
              <q-select
                v-model="postVisibility"
                :options="visibilityOptions"
                dense
                borderless
                options-dense
                style="min-width: 120px"
              />
            </div>
          </div>

          <q-input
            ref="postInput"
            v-model="postContent"
            placeholder="Sobre o que você está pensando?"
            type="textarea"
            autogrow
            borderless
            class="q-mb-md post-input"
          />

          <!-- Área de mídia -->
          <div v-if="mediaPreview" class="relative-position q-mb-md">
            <q-img :src="mediaPreview" style="border-radius: 8px; max-height: 300px" />
            <q-btn
              icon="close"
              round
              dense
              flat
              class="absolute-top-right bg-grey-9 text-white"
              style="margin: 4px"
              @click="mediaPreview = ''"
            />
          </div>

          <!-- Ações de mídia -->
          <div class="flex justify-between items-center q-pa-sm border-top">
            <div class="text-caption text-grey-7">Adicionar à publicação</div>
            <div>
              <q-btn round flat icon="photo" color="primary" @click="triggerFileInput('image')">
                <q-tooltip>Foto</q-tooltip>
                <input
                  ref="imageInput"
                  type="file"
                  accept="image/*"
                  style="display: none"
                  @change="handleFileUpload"
                />
              </q-btn>
              <q-btn
                round
                flat
                icon="videocam"
                color="primary"
                class="q-mx-xs"
                @click="triggerFileInput('video')"
              >
                <q-tooltip>Vídeo</q-tooltip>
                <input
                  ref="videoInput"
                  type="file"
                  accept="video/*"
                  style="display: none"
                  @change="handleFileUpload"
                />
              </q-btn>
              <q-btn round flat icon="insert_link" color="primary">
                <q-tooltip>Link</q-tooltip>
              </q-btn>
            </div>
          </div>
        </q-card-section>
      </q-card>
    </q-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, nextTick } from 'vue';
import { useQuasar } from 'quasar';
import type { QInput } from 'quasar';

const $q = useQuasar();

const emit = defineEmits<{
  (e: 'new-post', content: string, media?: string): void;
}>();

// Estado do componente
const modalOpen = ref(false);
const postContent = ref('');
const mediaPreview = ref('');
const postVisibility = ref('Público');
const visibilityOptions = ['Público', 'Amigos', 'Somente eu'];

// Refs
const postInput = ref<QInput | null>(null);
const imageInput = ref<HTMLInputElement | null>(null);
const videoInput = ref<HTMLInputElement | null>(null);
const userAvatar = 'https://cdn.quasar.dev/img/avatar.png';

// Estilo dinâmico
const dialogStyle = computed(() => ({
  width: $q.screen.lt.md ? '100%' : '500px',
  maxWidth: $q.screen.lt.md ? '100%' : '600px',
  borderRadius: $q.screen.lt.md ? '16px 16px 0 0' : '8px',
  position: $q.screen.lt.md ? 'fixed' : 'relative',
  bottom: $q.screen.lt.md ? '0' : 'auto',
}));

// Métodos
const openModal = async () => {
  modalOpen.value = true;
  await nextTick();
  postInput.value?.focus();
};

const closeModal = () => {
  modalOpen.value = false;
  postContent.value = '';
  mediaPreview.value = '';
};

const triggerFileInput = (type: 'image' | 'video') => {
  if (type === 'image') {
    imageInput.value?.click();
  } else {
    videoInput.value?.click();
  }
};

const handleFileUpload = (event: Event) => {
  const input = event.target as HTMLInputElement;
  if (input.files?.[0]) {
    mediaPreview.value = URL.createObjectURL(input.files[0]);
  }
};

const publishPost = () => {
  if (postContent.value.trim()) {
    emit('new-post', postContent.value, mediaPreview.value);
    closeModal();
  }
};
</script>

<style scoped>
.cursor-pointer {
  cursor: pointer;
}

.border-top {
  border-top: 1px solid rgba(0, 0, 0, 0.12);
}

.post-input {
  font-size: 1.1rem;
  line-height: 1.5;
  min-height: 100px;
}

.z-fab {
  z-index: 6000;
}

@media (max-width: 600px) {
  .q-dialog__inner--bottom > div {
    max-height: 80vh;
    overflow-y: auto;
  }
}
</style>
