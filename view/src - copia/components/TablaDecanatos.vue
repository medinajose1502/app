<template>
  <b-card>
    <b-card-header header-bg-variant="primary" header-text-variant="white">
      <h4>Lista de decanatos</h4>
    </b-card-header>
    <p></p>
    <b-card-body>
      <b-row>
        <b-col class="my-1">
          <b-form-group
            label="Filtro"
            label-cols-sm="3"
            label-align-sm="left"
            label-size="sm"
            label-for="filtro"
            class="mb-0"
          >
            <b-input-group size="sm">
              <b-form-input
                v-model="filtro"
                type="search"
                id="filtro"
                placeholder="Escriba algo para buscar"
              ></b-form-input>
              <b-input-group-append>
                <b-button :disabled="!filtro" @click="filtro = ''">Filtrar</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-row>
      <b-row>
        <b-table
          id="tabla-decanatos"
          hover
          :items="dec"
          :fields="fields"
          :filtro="filtro"
          primary-key="id"
          @row-clicked="verDecanato"
        ></b-table>
      </b-row>
      <b-row>
        <b-col>
          <b-pagination
            align="right"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="tabla-decanatos"
          ></b-pagination>
        </b-col>
      </b-row>
    </b-card-body>
    <b-card-footer footer-bg-variant="primary">
      <b-row>
        <b-col align="right">
          <router-link :to="{ name: 'AdminCrearDecanato'}">
            <b-button variant="info">
              Crear decanato
              <b-icon icon="plus-square-fill"></b-icon>
            </b-button>
          </router-link>
        </b-col>
      </b-row>
    </b-card-footer>
  </b-card>
</template>

<script>
export default {
  props: {
    items: {
      type: Array
    }
  },
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "nombre",
          sortable: true
        },
        {
          key: "ubicacion",
          label: "Ubicación",
          sortable: false
        }
      ],
      filtro: ""
    };
  },

  computed: {
    rows() {
      return this.dec.length;
    },
    dec() {
      return this.filtro
        ? this.items.filter(
            item =>
              item.nombre.includes(this.filtro) ||
              item.ubicacion.includes(this.filtro)
          )
        : this.items;
    }
  },
  methods: {
    verDecanato(item) {
      this.$router.push("/admin/decanato/ver/" + item.id);
    }
  }
};
</script>

<style>
</style>