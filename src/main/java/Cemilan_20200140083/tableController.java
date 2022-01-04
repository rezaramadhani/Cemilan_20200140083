package Cemilan_20200140083;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RezaRamadhani
 */

@Controller
public class tableController {
    
    @RequestMapping("/input")
    //@ResponseBody
    public String getData (HttpServletRequest data, Model discountprocess){
        
        String inputname = data.getParameter("var_name");
        
        String inputprice = data.getParameter("var_price");
        
        String inputquantity = data.getParameter("var_quantity");
        
        String uangcustomer = data.getParameter("var_uang");
        
        String diskon = "";
        
        Double Price = Double.valueOf(inputprice);
        Double Total = Double.valueOf(inputquantity);
        Double PriceTotal = Price * Total;
        Double getTotal = null;
        
        if (PriceTotal < 16000)
        {
            getTotal = PriceTotal - (0 * PriceTotal/100);
            diskon = "0%";
        }
        else if (PriceTotal >= 16000 && PriceTotal < 25000)
        {
            getTotal = PriceTotal - (10 * PriceTotal/100);
            diskon = "10%";
        }
        else if (PriceTotal >= 25000)
        {
            getTotal = PriceTotal - (15 * PriceTotal/100);
            diskon = "15%";
        }
        
        
        String Hasil = "";
        
        Double UPembeli = Double.valueOf(uangcustomer);
        Double Ukembalian = null; 
        Double Ukurang = null;
        
        if (UPembeli > getTotal)
        {
            Ukembalian = UPembeli - getTotal;
            Hasil = " kembalian sebesar Rp. "+Ukembalian;
        }
        else if (UPembeli < getTotal)
        {
            Ukurang = UPembeli - getTotal;
            Hasil = "Uang anda kurang Rp. "+Ukurang;
        }
                         
        discountprocess.addAttribute("name", inputname);
        discountprocess.addAttribute("price", inputprice);
        discountprocess.addAttribute("quantity", inputquantity);
        discountprocess.addAttribute("total", getTotal);
        discountprocess.addAttribute("diskon", diskon);
        discountprocess.addAttribute("UPemb", UPembeli);
        discountprocess.addAttribute("Hasil", Hasil);
        
        return "tableViewer";
    }
}