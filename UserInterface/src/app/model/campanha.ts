export class Campanha {
  id: number;
  nome = '';
  timeCoracao = '';
  vigenciaInicio = Date;
  vigenciaFim = Date;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
