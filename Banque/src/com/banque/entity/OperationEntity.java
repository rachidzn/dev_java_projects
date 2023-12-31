/**
 * Copyright : Iheb <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.entity;

import java.sql.Timestamp;

/**
 * Le bean qui represente une operation. <br>
 */
public class OperationEntity extends AbstractEntity implements IOperationEntity {

	private static final long serialVersionUID = 1L;

	private Timestamp date;
	private String libelle;
	private Double montant;

	private Integer compteId;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public OperationEntity() {
		this(null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            unid
	 * @param uneDate
	 *            l'id d'un compte
	 * @param unLibelle
	 *            le libelle du compte
	 * @param unMontant
	 *            un montant
	 */
	public OperationEntity(Integer unId, Timestamp uneDate, String unLibelle,
			Double unMontant) {
		super(unId);
		this.date = uneDate;
		this.libelle = unLibelle;
		this.montant = unMontant;
	}

	@Override
	public Timestamp getDate() {
		return this.date;
	}

	@Override
	public String getLibelle() {
		return this.libelle;
	}

	@Override
	public Double getMontant() {
		return this.montant;
	}

	@Override
	public void setDate(Timestamp uneDate) {
		if (uneDate == null) {
			this.date = new Timestamp(System.currentTimeMillis());
		} else {
			this.date = uneDate;
		}
	}

	@Override
	public void setLibelle(String unLibelle) {
		this.libelle = unLibelle;
	}

	@Override
	public void setMontant(Double unMontant) {
		this.montant = unMontant;
	}

	@Override
	public Integer getCompteId() {
		return this.compteId;
	}

	@Override
	public void setCompteId(Integer compteId) {
		this.compteId = compteId;
	}

	/**
	 * Donne une representation chainee de l'objet
	 *
	 * @return une representation chainee de l'objet
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String parent = super.toString();
		parent = parent.substring(0, parent.length() - 1);
		sb.append(parent);
		sb.append(",libelle=");
		sb.append(this.getLibelle());
		sb.append(",montant=");
		sb.append(this.getMontant());
		sb.append(",compteId=");
		sb.append(this.getCompteId());
		sb.append("}");
		return sb.toString();
	}
}