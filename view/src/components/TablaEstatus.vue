<template>
  <b-card class="shadow-soft">
    <b-card-header header-bg-variant="primary shadow-inset">
      <h4>Lista de estatus</h4>
    </b-card-header>
    <p></p>
    <b-card-body class="card bg-primary shadow-inset border-light">
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
            <b-input-group size="sm" class="filtrico">
              <b-form-input
                v-model="filtro"
                type="search"
                id="filtro"
                placeholder="Escriba algo para buscar"
              ></b-form-input>
              <b-input-group-append>
                <b-button
                  variant="primary rounded-right shadow-soft text-secondary"
                  :disabled="!filtro"
                  @click="filtro = ''"
                >Filtrar</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-row>
      <b-row>
        <b-table
          id="tabla-estatus"
          hover
          :items="est"
          :fields="fields"
          :filtro="filtro"
          :per-page="perPage"
          :current-page="currentPage"
          primary-key="id"
          @row-clicked="verEstatus"
        ></b-table>
      </b-row>
      <b-row>
        <b-col>
          <b-pagination
            align="right"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="tabla-estatus"
          ></b-pagination>
        </b-col>
      </b-row>
    </b-card-body>
    <b-card-footer footer-bg-variant="primary">
      <b-row>
        <b-col align="right">
          <router-link :to="{ name: 'AdminCrearEstatus'}">
            <b-button variant="primary text-info">
              Crear estatus
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
          key: "id",
          label: "Id",
          sortable: true
        },
        {
          key: "estado",
          label: "Estado",
          sortable: false
        }
      ],
      filtro: ""
    };
  },

  computed: {
    rows() {
      return this.est.length;
    },
    est() {
      return this.filtro
        ? this.items.filter(
            item =>
              item.id.includes(this.filtro) || item.estado.includes(this.filtro)
          )
        : this.items;
    }
  },
  methods: {
    verEstatus(item) {
      this.$router.push("/admin/estatus/ver/" + item.id);
    }
  }
};
</script>

<style>
</style>