<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h4>Reporte de actas mensuales por decanato</h4>
            </b-card-header>
            <b-card>
              <b-row>
                <b-col>
                  <b-form-group id="input-group-1" label="Año" label-for="input-1" align="left">
                    <b-form-input
                      id="input-1"
                      v-model="anno"
                      required
                      placeholder="Ingrese la cédula del usuario."
                    ></b-form-input>
                  </b-form-group>
                </b-col>
                <b-col>
                  <b-form-group id="input-group-2" label="Mes" label-for="input-2" align="left">
                    <b-form-select id="tipo" v-model="mes" class="mb-3" size="lg">
                      <b-form-select-option value="1">Enero</b-form-select-option>
                      <b-form-select-option value="2">Febrero</b-form-select-option>
                      <b-form-select-option value="3">Marzo</b-form-select-option>
                      <b-form-select-option value="4">Abril</b-form-select-option>
                      <b-form-select-option value="5">Mayo</b-form-select-option>
                      <b-form-select-option value="6">Junio</b-form-select-option>
                      <b-form-select-option value="7">Julio</b-form-select-option>
                      <b-form-select-option value="8">Agosto</b-form-select-option>
                      <b-form-select-option value="9">Septiembre</b-form-select-option>
                      <b-form-select-option value="10">Octubre</b-form-select-option>
                      <b-form-select-option value="11">Noviembre</b-form-select-option>
                      <b-form-select-option value="12">Diciembre</b-form-select-option>
                    </b-form-select>
                  </b-form-group>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-button @click="getDatos">Generar reporte</b-button>
                </b-col>
              </b-row>
              <p></p>
              <b-row>
                <b-col>
                  <b-card>
                    <b-card-body>
                      <Grafico
                        ref="skills_chart"
                        :chart-data="chartData"
                        :options="options"
                        id="chart"
                      ></Grafico>
                    </b-card-body>
                  </b-card>
                </b-col>
              </b-row>
            </b-card>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { Bar } from "vue-chartjs";
import ServiciosAPI from "@/services/ServiciosAPI.js";
import randomColor from "randomcolor";
import Grafico from "@/components/Grafico.vue";

const options = {
  responsive: true,
  maintainAspectRatio: false,
  animation: {
    animateRotate: false
  },
  legend: {
    display: false
  }
};

export default {
  components: {
    Grafico
  },
  data() {
    return {
      anno: 2020,
      mes: 1,
      datos: [],
      options,
      chartData: {
        labels: [],
        datasets: [
          {
            backgroundColor: [],
            data: []
          }
        ]
      }
    };
  },
  methods: {
    async getDatos() {
      var d = await ServiciosAPI.getReporteDecanato(this.anno, this.mes);
      if (d.status == 200) {
        this.datos = d.data;

        let lbl = [];
        let bgc = [];
        let dt = [];

        this.datos.forEach(ReporteDecanato => {
          lbl.push(ReporteDecanato.decanato);
          bgc.push(randomColor());
          dt.push(ReporteDecanato.actas);
        });

        let chartData = {
          labels: lbl,
          datasets: [
            {
              backgroundColor: bgc,
              data: dt
            }
          ]
        };

        this.chartData = chartData;
      } else console.log(d);
    }
  }
};
</script>

<style>
</style>