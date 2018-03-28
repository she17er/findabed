//
//  ShelterCardViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/26/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON


class ShelterCardViewController: UIViewController, UICollectionViewDelegateFlowLayout, UICollectionViewDataSource {

    var shelters:[Shelter] = []
    var filteredShelters:[Shelter] = []
    
    var isSearching = false

    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var collectionView: UICollectionView!
    override func viewDidLoad() {
        // Do any additional setup after loading the view.
        
        searchBar.delegate = self
        requestShelters()
    }
    
    func requestShelters() {
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/getShelters").validate().responseData { response in
            guard let data = response.data else { /* handle error? */ return }
            
            self.shelters = (try? JSONDecoder().decode([Shelter].self, from: data)) ?? []
            
            self.collectionView.reloadData()
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return shelters.count
    }
    
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: UIScreen.main.bounds.width, height: 227)
        
        
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! CollectionViewCell
        
        print(indexPath.row)
        var currentShelters:[Shelter] = []
        if (isSearching) {
            currentShelters = filteredShelters
        } else {
            currentShelters = shelters
        }
        
        var shelter = currentShelters[indexPath.row]
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
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        // get the shelter object
        self.performSegue(withIdentifier: "shelterViewIdentifier", sender: shelters[indexPath.row])
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? ShelterViewController {
            if let sendingValue = sender as? Shelter {
            
            destination.shelterName = sendingValue.name
            destination.currCapacity = "Current Capacity |  \(sendingValue.currCapacity)"
            destination.phoneNumber = "Phone Number \n\(sendingValue.phoneNumber)"
            destination.acceptedTypes = "ACCEPTED TYPES • \(sendingValue.acceptedTypes[0])"
            var coOrdinates = sendingValue.coOrdinates.components(separatedBy: ",")
            destination.latitude = Double(coOrdinates[1])!
            destination.longitude = Double(coOrdinates[0])!
            destination.id = sendingValue._id
            }
        }
    }

    
    
}

extension ShelterCardViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if (searchText == "") {
            isSearching = false
        } else {
            isSearching = true
        }
        filteredShelters = shelters.filter({ shelter in
            return shelter.name.lowercased().contains(searchText.lowercased())
        })
        collectionView.reloadData()
    }
}
