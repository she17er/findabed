//
//  registrationViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/27/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit

class registrationViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    @IBOutlet weak var rolePickerView: UIPickerView!
    let roles = ["user", "shelter"]
    var roleTxt: String = ""
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1;
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return roles.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return roles[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        roleTxt = roles[row]
    }
    
    
    
   
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        rolePickerView?.delegate = self
        rolePickerView?.dataSource = self
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBAction func nextBtn(_ sender: Any) {
        if roleTxt == "user" {
            self.performSegue(withIdentifier: "userRegIdentifier", sender:self)
        } else if roleTxt == "shelter" {
            self.performSegue(withIdentifier: "shelterRegIdentifier", sender:self)
        }
    }
    
}
