//
//  BedBookViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/28/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import Alamofire

class BedBookViewController: UIViewController {

    @IBOutlet weak var adultCounter: UILabel!
    @IBOutlet weak var childCounter: UILabel!
    @IBOutlet weak var youngAdultCounter: UILabel!
    var id:String = ""
    var currCapacity: Int = 0
    var cancelBtn: UIButton!
    var adultIncrement: UIButton!
    var adultDecrement: UIButton!
    var childIncrement: UIButton!
    var childDecrement: UIButton!
    var YoungAdultIncrement: UIButton!
    var YoungAdultDecrement: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        cancelBtn = makeCancelButton(xVal: 16, yVal: 28)
        
        adultIncrement = makePlusButton(xVal: 329, yVal: 113)
        
        adultDecrement = makeMinusButton(xVal: 249, yVal: 113)
        
        childDecrement = makeMinusButton(xVal: 249, yVal: 232)
        
        childIncrement = makePlusButton(xVal: 329, yVal: 232)
        
        YoungAdultIncrement = makePlusButton(xVal: 329, yVal: 387)
        
        YoungAdultDecrement = makeMinusButton(xVal: 249, yVal: 387)
        
        adultIncrement.addTarget(self, action: #selector(self.AdultBedIncrementer), for: .touchUpInside)
        
        adultDecrement.addTarget(self, action: #selector(self.AdultBedDecrementer), for: .touchUpInside)
        
        childIncrement.addTarget(self, action: #selector(self.ChildBedIncrementer), for: .touchUpInside)
        
        childDecrement.addTarget(self, action: #selector(self.ChildBedDecrementer), for: .touchUpInside)
        
        YoungAdultIncrement.addTarget(self, action: #selector(self.YoungAdultBedIncrementer), for: .touchUpInside)
        
        
        YoungAdultDecrement.addTarget(self, action: #selector(self.YoungAdultBedDecrementer), for: .touchUpInside)
        
        cancelBtn.addTarget(self, action: #selector(self.GoBack), for: .touchUpInside)
    }
    
    @objc func AdultBedIncrementer() {
        adultCounter.text = "\(Int(adultCounter.text!)! + 1)"
    }
    
    @objc func AdultBedDecrementer() {
        adultCounter.text = "\(Int(adultCounter.text!)! - 1)"
    }
    
    @objc func ChildBedIncrementer() {
        childCounter.text = "\(Int(childCounter.text!)! + 1)"
    }
    
    @objc func ChildBedDecrementer() {
        childCounter.text = "\(Int(childCounter.text!)! - 1)"
    }
    
    @objc func YoungAdultBedIncrementer() {
        youngAdultCounter.text = "\(Int(youngAdultCounter.text!)! + 1)"
    }
    
    @objc func YoungAdultBedDecrementer() {
        youngAdultCounter.text = "\(Int(youngAdultCounter.text!)! - 1)"
    }
    
    @objc func GoBack() {
        self.navigationController?.popViewController(animated: true)
    }
    
    func makePlusButton(xVal: Int, yVal: Int) -> UIButton {
        let button = UIButton(type: .custom)
        button.frame = CGRect(x: xVal, y: yVal, width: 30, height: 30)
        button.layer.cornerRadius = 0.5 * button.bounds.size.width
        button.clipsToBounds = true
        button.backgroundColor = .clear
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.white.cgColor
        button.setTitle("＋", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 23)
        view.addSubview(button)
        return button
    }
    
    func makeMinusButton(xVal: Int, yVal: Int) -> UIButton {
        let button = UIButton(type: .custom)
        button.frame = CGRect(x: xVal, y: yVal, width: 30, height: 30)
        button.layer.cornerRadius = 0.5 * button.bounds.size.width
        button.clipsToBounds = true
        button.backgroundColor = .clear
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.white.cgColor
        button.setTitle("－", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 23)
        view.addSubview(button)
        return button
    }
    
    func makeCancelButton(xVal: Int, yVal: Int) -> UIButton {
        let button = UIButton(type: .custom)
        button.frame = CGRect(x: xVal, y: yVal, width: 30, height: 30)
        button.clipsToBounds = true
        button.backgroundColor = .clear
        button.setTitle("╳", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 23)
        view.addSubview(button)
        return button
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    @IBAction func onBedBook(_ sender: Any) {
        let beds = Int(adultCounter.text!)! + Int(childCounter.text!)! + Int(youngAdultCounter.text!)!
        
        let parameters: Parameters = [
        
            "currCapacity": "\(currCapacity + beds)"
        ]
        
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/updateCapacity/\(id)", method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: nil).responseString {
            response in
            print (response)
        }
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
