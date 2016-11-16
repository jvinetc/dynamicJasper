/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebadynamicjasper;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.util.SortUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Natalia
 */
public class PruebaDynamicJasper extends ReportBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        PruebaDynamicJasper pdj = new PruebaDynamicJasper();
        pdj.generarReporte();
        pdj.exportarDesdeJrxml();
        JasperViewer.viewReport(pdj.jp);
        JasperDesignViewer.viewReportDesign(pdj.jr);
    }

    @Override
    public DynamicReport construirReporte() throws Exception {
        FastReportBuilder drd = new FastReportBuilder();
        drd.addColumn("Nombre", "nombre", String.class.getName(), 20).addColumn("Direccion", "direccion", String.class.getName(), 20).addColumn("Pais", "pais", String.class.getName(), 20).addColumn("Codigo", "codigo", Integer.class.getName(), 20).setTitle("primer reporte con dynamicJasper").setSubtitle("Creado el " + new Date()).setPrintBackgroundOnOddRows(Boolean.TRUE).setUseFullPageWidth(Boolean.TRUE);
        AbstractColumn columnFecha = ColumnBuilder.getNew().setColumnProperty("fecha", Date.class.getName()).setTitle("fecha actual").setWidth(28).setPattern("dd/MM/yyyy").build();
        drd.addColumn(columnFecha);
        return drd.build();
    }

    @Override
    public JRDataSource getDataSource() {
        Collection<ClasePrueba> data = getDataOneReportMock();
        data = SortUtils.sortCollection(data, dr.getColumns());
        JRDataSource ds = new JRBeanCollectionDataSource(data);
        return ds;
    }

    private Collection<ClasePrueba> getDataOneReportMock() {

        ClasePrueba elementos;
        Collection<ClasePrueba> dataResult = new ArrayList<ClasePrueba>();

        for (int i = 0; i < 10; i++) {
            elementos = new ClasePrueba();
            elementos.setNombre("nombre " + i);
            elementos.setDireccion("direccion " + i);
            elementos.setPais("pais" + i);
            elementos.setCodigo(123450 + i);
            elementos.setFecha(new Date());
            dataResult.add(elementos);
        }


        return dataResult;
    }
}
