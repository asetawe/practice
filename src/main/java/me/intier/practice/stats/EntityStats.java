package me.intier.practice.stats;

import java.util.UUID;

public class EntityStats {
    private UUID entityId;

    public UUID getEntityId() {
        return entityId;
    }

    // 기본 스탯
    private double baseHealth = 100.0;
    private double currentHealth = baseHealth;
    private double baseMana = 100.0;
    private double currentMana = baseMana;
    private double baseDamage = 10.0;
    private double baseDefense = 0.0;
    private double healthRegen = 5.0; // 초당 체력 회복량 (백분율)
    private double manaRegen = 5.0; // 초당 마나 회복량 (백분율)
    private double resistance = 1.0;

    // 추가 스탯
    private double baseHealthBoost = 0.0;
    private double baseManaBoost = 0.0;
    private double baseDamageBoost = 0.0;
    private double baseDefenseBoost = 0.0;

    // 추가 스탯 보너스 (%)
    private double healthIncrease = 100.0;
    private double manaIncrease = 100.0;
    private double damageIncrease = 100.0;
    private double defenseIncrease = 100.0;

    // 속성 대미지 보너스 (%)
    private double fireDamageBonus = 100.0;
    private double waterDamageBonus = 100.0;
    private double iceDamageBonus = 100.0;
    private double dragonDamageBonus = 100.0;
    private double physicalDamageBonus = 100.0;

    // 속성 내성
    private double fireResistance = 0.0;
    private double waterResistance = 0.0;
    private double iceResistance = 0.0;
    private double dragonResistance = 0.0;
    private double physicalResistance = 0.0;

    // 체력 메서드
    public void setBaseHealth(double baseHealth) {
        this.baseHealth = baseHealth;
    }

    public double getBaseHealth() {
        return this.baseHealth;
    }

    public void setBaseHealthBoost(double boost) {
        this.baseHealthBoost = boost;
    }

    public double getBaseHealthBoost() {
        return this.baseHealthBoost;
    }

    public void setHealthIncrease(double increase) {
        this.healthIncrease = increase;
    }

    public double getHealthIncrease() {
        return this.healthIncrease;
    }

    public double getFinalHealth() {
        return (baseHealth + baseHealthBoost) * (healthIncrease / 100.0);
    }

    public void setHealthRegen(double healthRegen) {
        this.healthRegen = healthRegen;
    }

    public double getHealthRegen() {
        return this.healthRegen;
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = Math.min(currentHealth, getFinalHealth()); // 현재 체력은 최대 체력을 초과할 수 없음
    }

    public void regenerateHealth() {
        this.currentHealth += (getFinalHealth() * (healthRegen / 100.0));
        setCurrentHealth(this.currentHealth); // 최대 체력을 초과하지 않도록 조정
    }

    // 마나 메서드
    public void setBaseMana(double baseMana) {
        this.baseMana = baseMana;
    }

    public double getBaseMana() {
        return this.baseMana;
    }

    public void setBaseManaBoost(double boost) {
        this.baseManaBoost = boost; // 변수 이름 오류 수정
    }

    public double getBaseManaBoost() {
        return this.baseManaBoost; // 변수 이름 오류 수정
    }

    public void setManaIncrease(double increase) {
        this.manaIncrease = increase;
    }

    public double getManaIncrease() {
        return this.manaIncrease;
    }

    public void setManaRegen(double manaRegen) {
        this.manaRegen = manaRegen;
    }

    public double getManaRegen() {
        return this.manaRegen;
    }

    public double getMaxMana() {
        return (baseMana + baseManaBoost) * (manaIncrease / 100.0);
    }

    public double getCurrentMana() {
        return this.currentMana;
    }

    public void setCurrentMana(double currentMana) {
        this.currentMana = Math.min(currentMana, getMaxMana()); // 현재 마나는 최대 마나를 초과할 수 없음
    }

    public void regenerateMana() {
        this.currentMana += (getMaxMana() * (manaRegen / 100.0));
        setCurrentMana(this.currentMana); // 최대 마나를 초과하지 않도록 조정
    }

    // 공격력(Damage) 관련 메서드
    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getBaseDamage() {
        return this.baseDamage;
    }

    public void setBaseDamageBoost(double boost) {
        this.baseDamageBoost = boost;
    }

    public double getBaseDamageBoost() {
        return this.baseDamageBoost;
    }

    public void setDamageIncrease(double increase) {
        this.damageIncrease = increase;
    }

    public double getDamageIncrease() {
        return this.damageIncrease;
    }

     public double getFinalDamage() {
        return (baseDamage + baseDamageBoost) * (damageIncrease / 100.0);
    }

    // 저항 메서드
    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public double getResistance() {
        return this.resistance;
    }

    public double getFinalResistance() {
        return (1 / resistance);
    }

    // 방어력 관련 메서드
    public void setBaseDefense(double baseDefense) {
        this.baseDefense = baseDefense;
    }

    public double getBaseDefense() {
        return this.baseDefense;
    }

    public void setBaseDefenseBoost(double baseDefenseBoost) {
        this.baseDefenseBoost = baseDefenseBoost;
    }

    public double getBaseDefenseBoost() {
        return this.baseDefenseBoost;
    }

    public void setDefenseIncrease(double defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }

    public double getDefenseIncrease() {
        return this.defenseIncrease;
    }

    public double getFinalDefense() {
        return (baseDefense + baseDefenseBoost) * (defenseIncrease / 100);
    }

    // 속성 피해 보너스 메서드
    public void setFireDamageBonus(double fireDamageBonus) {
        this.fireDamageBonus = fireDamageBonus;
    }

    public double getFireDamageBonus() {
        return this.fireDamageBonus;
    }

    public void setWaterDamageBonus(double waterDamageBonus) {
        this.waterDamageBonus = waterDamageBonus;
    }

    public double getWaterDamageBonus() {
        return this.waterDamageBonus;
    }

    public void setIceDamageBonus(double iceDamageBonus) {
        this.iceDamageBonus = iceDamageBonus;
    }

    public double getIceDamageBonus() {
        return this.iceDamageBonus;
    }

    public void setDragonDamageBonus(double dragonDamageBonus) {
        this.dragonDamageBonus = dragonDamageBonus;
    }

    public double getDragonDamageBonus() {
        return this.dragonDamageBonus;
    }

    public void setPhysicalDamageBonus(double physicalDamageBonus) {
        this.physicalDamageBonus = physicalDamageBonus;
    }

    public double getPhysicalDamageBonus() {
        return this.physicalDamageBonus;
    }

    // 속성 내성
    public void setFireResistance(double fireResistance) {
        this.fireResistance = fireResistance;
    }

    public double getFireResistance() {
        return this.fireResistance;
    }

    public void setWaterResistance(double waterResistance) {
        this.waterResistance = waterResistance;
    }

    public double getWaterResistance() {
        return this.waterResistance;
    }

    public void setIceResistance(double iceResistance) {
        this.iceResistance = iceResistance;
    }

    public double getIceResistance() {
        return this.iceResistance;
    }

    public void setDragonResistance(double dragonResistance) {
        this.dragonResistance = dragonResistance;
    }

    public double getDragonResistance() {
        return this.dragonResistance;
    }

    public void setPhysicalResistance(double physicalResistance) {
        this.physicalResistance = physicalResistance;
    }

    public double getPhysicalResistance() {
        return this.physicalResistance;
    }
    public double getFinalCalculatedDamage(){
        return (getFinalDamage() + get
    }
}