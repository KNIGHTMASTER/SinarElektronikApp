/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.PenjualanBarangBesar.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class ProsesKasir {
       private String kode, nama, penjual;
       
       private int jml, harga, potongan, tambahan, total, no, profit, bonuslangsung, bonuskumulatif;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getPotongan() {
        return potongan;
    }

    public void setPotongan(int potongan) {
        this.potongan = potongan;
    }

    public int getTambahan() {
        return tambahan;
    }

    public void setTambahan(int tambahan) {
        this.tambahan = tambahan;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getBonuslangsung() {
        return bonuslangsung;
    }

    public void setBonuslangsung(int bonuslangsung) {
        this.bonuslangsung = bonuslangsung;
    }

    public int getBonuskumulatif() {
        return bonuskumulatif;
    }

    public void setBonuskumulatif(int bonuskumulatif) {
        this.bonuskumulatif = bonuskumulatif;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.kode);
        hash = 61 * hash + Objects.hashCode(this.nama);
        hash = 61 * hash + Objects.hashCode(this.penjual);
        hash = 61 * hash + this.jml;
        hash = 61 * hash + this.harga;
        hash = 61 * hash + this.potongan;
        hash = 61 * hash + this.tambahan;
        hash = 61 * hash + this.total;
        hash = 61 * hash + this.no;
        hash = 61 * hash + this.profit;
        hash = 61 * hash + this.bonuslangsung;
        hash = 61 * hash + this.bonuskumulatif;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProsesKasir other = (ProsesKasir) obj;
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.penjual, other.penjual)) {
            return false;
        }
        if (this.jml != other.jml) {
            return false;
        }
        if (this.harga != other.harga) {
            return false;
        }
        if (this.potongan != other.potongan) {
            return false;
        }
        if (this.tambahan != other.tambahan) {
            return false;
        }
        if (this.total != other.total) {
            return false;
        }
        if (this.no != other.no) {
            return false;
        }
        if (this.profit != other.profit) {
            return false;
        }
        if (this.bonuslangsung != other.bonuslangsung) {
            return false;
        }
        if (this.bonuskumulatif != other.bonuskumulatif) {
            return false;
        }
        return true;
    }
      
}
