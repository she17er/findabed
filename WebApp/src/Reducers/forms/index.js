import { combineForms} from 'react-redux-form';

import shelter from './shelter';
import user from './user';


export default combineForms({
  user,
  shelter,
}, 'forms');
