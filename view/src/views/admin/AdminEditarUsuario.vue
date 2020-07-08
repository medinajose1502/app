<template>
  <div>Hola</div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
export default {
  props: ["id"],
  data() {
    return {
      decanatos: [],
      usuario: {
        cedula: "",
        contrasenna: "",
        nombres: "",
        apellidos: "",
        roles: [],
        decanato: {
          id: ""
        }
      },
      show: true,
      opc: []
    };
  },
  async created() {
    var dec = await ServiciosAPI.getDecanatos();
    if (dec.status == 200) this.decanatos = dec.data;

    var usu = await ServiciosAPI.getUsuarioId(this.id);
    if (usu.status == 200) this.usuario = usu.data;
  }
};
</script>

<style>
</style>