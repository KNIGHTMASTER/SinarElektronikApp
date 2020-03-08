package Sinarelektronikapp.transaction.barangbesar.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBB {
    int idtransaksi;
    String user;
    Date tanggal;
    String jam;
    int total;
    int pembayaran;
    int sisa;
    int profit;
    String penjual;
    int bonusLangsung;
    int bonusKumuatif;

    public int getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(int idtransaksi) {
        this.idtransaksi = idtransaksi;
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

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public int getBonusLangsung() {
        return bonusLangsung;
    }

    public void setBonusLangsung(int bonusLangsung) {
        this.bonusLangsung = bonusLangsung;
    }

    public int getBonusKumuatif() {
        return bonusKumuatif;
    }

    public void setBonusKumuatif(int bonusKumuatif) {
        this.bonusKumuatif = bonusKumuatif;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idtransaksi;
        hash = 31 * hash + Objects.hashCode(this.user);
        hash = 31 * hash + Objects.hashCode(this.tanggal);
        hash = 31 * hash + Objects.hashCode(this.jam);
        hash = 31 * hash + this.total;
        hash = 31 * hash + this.pembayaran;
        hash = 31 * hash + this.sisa;
        hash = 31 * hash + this.profit;
        hash = 31 * hash + Objects.hashCode(this.penjual);
        hash = 31 * hash + this.bonusLangsung;
        hash = 31 * hash + this.bonusKumuatif;
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
        final TransaksiPenjualanBB other = (TransaksiPenjualanBB) obj;
        if (this.idtransaksi != other.idtransaksi) {
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
        if (!Objects.equals(this.penjual, other.penjual)) {
            return false;
        }
        if (this.bonusLangsung != other.bonusLangsung) {
            return false;
        }
        if (this.bonusKumuatif != other.bonusKumuatif) {
            return false;
        }
        return true;
    }
        
}
