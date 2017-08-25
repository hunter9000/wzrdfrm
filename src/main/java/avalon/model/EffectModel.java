//package com.test.model;
//
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.*;
//
////@Entity
////@Table(name="effect")
//public class EffectModel {
//
////    @Id
////    @GeneratedValue(strategy = GenerationType.AUTO)
////    @Column(name = "id")
////    private long id;
////
////    @Column(name = "type")
////    private EffectType type;
////
////    @OneToOne(cascade=CascadeType.ALL)
////    @JoinTable(name="material_effect",
////            joinColumns={@JoinColumn(name="effect_id", referencedColumnName="id")},
////            inverseJoinColumns={@JoinColumn(name="material_id", referencedColumnName="id")})
////    private MaterialEffectModel materialEffect;
////
////    public long getId() {
////        return id;
////    }
////    public void setId(long id) {
////        this.id = id;
////    }
////
////    public EffectType getType() {
////        return type;
////    }
////    public void setType(EffectType type) {
////        this.type = type;
////    }
//
//}
