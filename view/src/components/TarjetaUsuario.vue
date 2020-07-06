<template>
  <b-card>
    <b-container fluid>
      <b-row>
        <b-col cols="10">
          <p
            align="left"
          >Fecha de la sesión: {{usuario.fecha}} | Tipo de sesión: {{tipoSesion}} | Acta número: {{usuario.id}}</p>
          <p align="left">Resumen de la descripción: {{resumenDesc}}</p>
        </b-col>
        <b-col>
          <router-link :to="{ name: 'AdminVerUsuario', params: {id: usuario.id} }">
            <b-button variant="warning">
              Ver Acta
              <b-icon-search></b-icon-search>
            </b-button>
          </router-link>
        </b-col>
      </b-row>
    </b-container>
  </b-card>
</template>

<script>
export default {
  props: ["usuario"],
  computed: {
    tipoSesion: function() {
      if (this.usuario.tipo == "E") return "Extraordinaria";
      else return "Ordinaria";
    },

    resumenDesc: function() {
      return this.usuario.descripcion.slice(0, 30) + "...";
    }
  }
};
</script>

<style>
</style>