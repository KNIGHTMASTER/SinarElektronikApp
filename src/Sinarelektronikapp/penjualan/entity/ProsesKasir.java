/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class ProsesKasir {
       private String kode, nama;
       
       private int jml, harga, potongan, tambahan, total, no, profit;

    public int getTambahan() {
        return tambahan;
    }

    public void setTambahan(int tambahan) {
        this.tambahan = tambahan;
    }
              

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
       
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
              

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.kode);
        hash = 11 * hash + Objects.hashCode(this.nama);
        hash = 11 * hash + this.jml;
        hash = 11 * hash + this.harga;
        hash = 11 * hash + this.potongan;
        hash = 11 * hash + this.total;
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
        if (this.jml != other.jml) {
            return false;
        }
        if (this.harga != other.harga) {
            return false;
        }
        if (this.potongan != other.potongan) {
            return false;
        }
        if (this.total != other.total) {
            return false;
        }
        return true;
    }
              
}
