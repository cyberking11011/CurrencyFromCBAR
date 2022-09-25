/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author RoRSh-PC
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valute {
    private String code;
    private String nominal;
    private String name;
    private double value;
    
}
