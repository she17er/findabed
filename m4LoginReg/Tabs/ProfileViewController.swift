//
//  ProfileViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/26/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit

class ProfileViewController: UIViewController, UICollectionViewDelegateFlowLayout, UICollectionViewDataSource {

    var isBooked: Bool = false
    var bookedShelter: Shelter?
    
  
    @IBOutlet weak var collectionView: UICollectionView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        requestShelter()
        
        collectionView.delegate = self
        collectionView.dataSource = self
    }
    
    override func viewWillAppear(_ animated: Bool) { // called every time
        requestShelter()
    }
    
    func requestShelter() {
        
        isBooked = ShelterPersistence.isBooked()
        if (isBooked) {
            bookedShelter = ShelterPersistence.getBookedShelter()[0]
        }
        
        
        
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 1
    }
    
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        // get the shelter object
        self.performSegue(withIdentifier: "cancelShelterIdentifier", sender: ShelterPersistence.getBookedShelter()[0])
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! CollectionViewCell
        
        print(indexPath.row)
        var currentShelters:[Shelter] = ShelterPersistence.getBookedShelter()
        
        var shelter = currentShelters[0]
        cell.labelName.text = shelter.name
        cell.acceptedTypesLabel.text = "ACCEPTED TYPES • \(shelter.acceptedTypes[0])"
        cell.phoneNumberLabel.text = "Phone Number \n\(shelter.phoneNumber)"
        cell.currCapacityLabel.text = "Current Capacity |  \(shelter.currCapacity)"
        //This creates the shadows and modifies the cards a little bit
        cell.contentView.layer.cornerRadius = 4.0
        cell.contentView.layer.borderWidth = 1.0
        cell.contentView.layer.borderColor = UIColor.clear.cgColor
        cell.contentView.layer.masksToBounds = false
        cell.layer.shadowColor = UIColor.gray.cgColor
        cell.layer.shadowOffset = CGSize(width: 0, height: 1.0)
        cell.layer.shadowRadius = 4.0
        cell.layer.shadowOpacity = 1.0
        cell.layer.masksToBounds = false
        cell.layer.shadowPath = UIBezierPath(roundedRect: cell.bounds, cornerRadius: cell.contentView.layer.cornerRadius).cgPath
        
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? CancelShelterViewController {
            if let sendingValue = sender as? Shelter {
                
                destination.shelterName = sendingValue.name
                destination.currCapacity = "\(sendingValue.currCapacity)"
                destination.phoneNumber = "\(sendingValue.phoneNumber)"
                destination.acceptedTypes = sendingValue.acceptedTypes[0]
                var coOrdinates = sendingValue.coOrdinates.components(separatedBy: ",")
                destination.latitude = Double(coOrdinates[1])!
                destination.longitude = Double(coOrdinates[0])!
                destination.id = sendingValue._id
            }
        }
    }
    

}
