import { type Curtida, curtiPublicacao } from 'src/domain/CurtidaService';
import {} from 'vue';

export function useCurtiPublicação() {
  const curtida = async (curti: Curtida) => {
    await curtiPublicacao(curti);
  };

  return {
    curtida,
  };
}
