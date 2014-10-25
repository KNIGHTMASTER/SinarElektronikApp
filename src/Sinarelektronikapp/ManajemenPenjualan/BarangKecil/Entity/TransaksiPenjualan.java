/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualan {
    int idTransaksi;
    String user;
    Date tanggal;
    String jam;
    int total;
    int pembayaran;
    int sisa;
    int profit;

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(int pembayaran) {
        this.pembayaran = pembayaran;
    }

    public int getSisa() {
        return sisa;
    }

    public void setSisa(int sisa) {
        this.sisa = sisa;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idTransaksi;
        hash = 67 * hash + Objects.hashCode(this.user);
        hash = 67 * hash + Objects.hashCode(this.tanggal);
        hash = 67 * hash + Objects.hashCode(this.jam);
        hash = 67 * hash + this.total;
        hash = 67 * hash + this.pembayaran;
        hash = 67 * hash + this.sisa;
        hash = 67 * hash + this.profit;
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
        final TransaksiPenjualan other = (TransaksiPenjualan) obj;
        if (this.idTransaksi != other.idTransaksi) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        if (!Objects.equals(this.jam, other.jam)) {
            return false;
        }
        if (this.total != other.total) {
            return false;
        }
        if (this.pembayaran != other.pembayaran) {
            return false;
        }
        if (this.sisa != other.sisa) {
            return false;
        }
        if (this.profit != other.profit) {
            return false;
        }
        return true;
    }        
}
