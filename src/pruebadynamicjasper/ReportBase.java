/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebadynamicjasper;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Natalia
 */
public abstract class ReportBase {
    protected static final Log log= LogFactory.getLog(ReportBase.class);
    protected JasperPrint jp;
    protected JasperReport jr;
    protected Map parametros= new HashMap();
    protected DynamicReport dr;
    
    public abstract DynamicReport construirReporte()throws Exception;
    public abstract JRDataSource getDataSource();
    
    public void generarReporte() throws Exception{
        dr= construirReporte();
        JRDataSource ds= getDataSource();
        jr= DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), parametros);
        log.debug("creando reporte");
        if(ds!=null){
            jp=JasperFillManager.fillReport(jr, parametros,ds);
        }else{
           jp= JasperFillManager.fillReport(jr, parametros);
           log.debug("creacion completa");
           log.debug("exportar reporte");
        }
        exportReport();
        log.debug("prueba finalizada");
    }
    
    protected LayoutManager getLayoutManager(){
        return new ClassicLayoutManager();
    }

    protected void exportReport() throws Exception{
       final String path=System.getProperty("user.dir")+"/prueba.pdf";
       System.out.println(("exportande el pdf a "+path));
       log.debug("exportande el pdf a "+path);
       JRPdfExporter pdf= new JRPdfExporter();
       File salida= new File(path);
       File pariente= salida.getParentFile();
       if(pariente != null){
           pariente.mkdirs();
       }
       FileOutputStream fos = new FileOutputStream(salida);
       pdf.setParameter(JRExporterParameter.JASPER_PRINT, jp);
       pdf.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
       pdf.exportReport();
       log.debug("reorte exportado en "+ path);
    }
    
    protected void exportarDesdeJrxml() throws Exception{
        if(this.jr != null){
            DynamicJasperHelper.generateJRXML(this.jr, 
                    "UTF-8", System.getProperty("user.dir")+"/prueba.jrxml");
        }else{
            DynamicJasperHelper.generateJRXML(this.dr, 
                    this.getLayoutManager(), this.parametros,"UTF-8",
                    System.getProperty("user.dir")+this.getClass().
                    getCanonicalName()+".jrxml");
        }
    }
}
